package com.shoppingapp.common.vo;

public enum SensitivityCharacteristic {
    NONE(0),
    ELECTRICITY_CONSUMPTION(1),
    STORAGE_SPACE_CONSUMPTION(2);

    private int characteristicNumber;

    SensitivityCharacteristic(int i) {
        this.characteristicNumber = i;
    }

    public static SensitivityCharacteristic valueOf(int value) {
        for (SensitivityCharacteristic r : SensitivityCharacteristic.values()) {
            if (r.getCharacteristicNumber() == value) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getCharacteristicNumber() {
        return characteristicNumber;
    }
}
