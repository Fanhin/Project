package com.example.tripbuddyv2.ModelsV2;

public class ItemV2 {
    private int type;
    private Object object;

    public ItemV2(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
