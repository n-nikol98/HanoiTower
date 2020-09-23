package com;

import com.hanoitower.HanoiTower;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            HanoiTower ht = new HanoiTower(3);

            ht.distributeDisks();

            List<HanoiTower.DiskDistributionStep> result = ht.getDiskDistributionSteps();

            result.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
