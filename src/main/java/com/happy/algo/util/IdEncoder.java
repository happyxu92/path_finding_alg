package com.happy.algo.util;

import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: create by happy
 * @date: 2021/3/31
 */
public class IdEncoder {
    public static <T> Map<T, Integer> encodeIds(List<Triple<T, T, Double>> edges) {
        Map<T, Integer> encodeMap = new HashMap<>();

        int count = 0;
        Set<T> fromIds = edges.stream().map(Triple::getLeft).collect(Collectors.toSet());
        for (T id: fromIds) {
            encodeMap.put(id, count++);
        }

        Set<T> toIds = edges.stream().map(Triple::getMiddle).collect(Collectors.toSet());
        for (T id: toIds) {
            if (!fromIds.contains(id)) {
                encodeMap.put(id, count++);
            }
        }

        return encodeMap;
    }
}
