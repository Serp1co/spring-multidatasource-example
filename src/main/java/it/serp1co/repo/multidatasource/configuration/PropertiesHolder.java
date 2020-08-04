package it.serp1co.repo.multidatasource.configuration;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PropertiesHolder {
    private final Map<String, String> properties = new HashMap<>();
}
