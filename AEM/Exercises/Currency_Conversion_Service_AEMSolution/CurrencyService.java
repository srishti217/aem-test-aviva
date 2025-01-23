package com.aviva.core.service;

import java.util.List;
import java.util.Map;

public interface CurrencyService {
    Map<String, CurrencyData> convertPrice(double price, List<String> currencies);
}
