package com.codewizards.meshify.framework.controllers;

import android.bluetooth.BluetoothDevice;

import androidx.annotation.Nullable;

import com.codewizards.meshify.framework.entities.MeshifyEntity;

public class Transaction implements Comparable {

    private TransactionManager transactionManager;

    private MeshifyEntity meshifyEntity;

    private Session session;

    private String start;

    private BluetoothDevice bluetoothDevice;

    Transaction(Session session, MeshifyEntity meshifyEntity, TransactionManager transactionManager) {
        this.session = session;

        this.meshifyEntity = meshifyEntity;
        this.start = String.valueOf(System.currentTimeMillis());
        this.transactionManager = transactionManager;
        if (session.getDevice() == null) {
            throw new IllegalArgumentException("BluetoothDevice is null.");
        }
        this.bluetoothDevice = session.getDevice().getBluetoothDevice();
    }

    public String getStart() {
        return start;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Transaction) {
            return this.start.equalsIgnoreCase(((Transaction) obj).getStart());
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {

        if (o instanceof Transaction) {
            return this.start.compareTo(((Transaction) o).getStart());
        }

        throw new IllegalArgumentException(o.getClass().getName() + " is not a " + this.getClass().getName());
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.bluetoothDevice;
    }


    public synchronized Session getSession() {
        return this.session;
    }


    TransactionManager getTransactionManager() {
        return this.transactionManager;
    }

    public MeshifyEntity getMeshifyEntity() {
        return this.meshifyEntity;
    }
}
