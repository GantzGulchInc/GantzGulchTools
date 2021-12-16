package com.gantzgulch.logging.core.impl;

import java.util.Comparator;

public class GGLoggerFactoryComparator implements Comparator<GGLoggerFactory> {

    @Override
    public int compare(final GGLoggerFactory o1, final GGLoggerFactory o2) {
        return o1.getPriority() - o2.getPriority();
    }

}
