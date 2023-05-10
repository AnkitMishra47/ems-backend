package com.ems.model.types;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Gender {
    public static final Gender MALE = new Gender("MALE", "Male");
    public static final Gender FEMALE = new Gender("FEMALE", "Female");
    public static final Gender OTHER = new Gender("OTHER", "Others");

    private final String value;
    private final String description;

    private Gender(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Gender fromValue(String value) {
        for (Gender gender : getAllGenders()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        return null;
    }

    public static List<Gender> getAllGenders() {
        List<Gender> genders = new ArrayList<>();

        try {
            Field[] fields = Gender.class.getFields();
            for (Field field : fields) {
                Object obj = field.get(null);
                if (obj instanceof Gender) {
                    Gender gender = (Gender) obj;
                    genders.add(gender);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return genders;
    }
}




