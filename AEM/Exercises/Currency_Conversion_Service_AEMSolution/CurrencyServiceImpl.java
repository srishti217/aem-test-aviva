package com.aviva.core.service.impl;

import com.aviva.core.service.CurrencyService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = CurrencyService.class)
public class CurrencyServiceImpl implements CurrencyService {

    @Reference
    private ResourceResolver resourceResolver;

    @Override
    public Map<String, CurrencyData> convertPrice(double price, List<String> currencies) {
        Map<String, CurrencyData> convertedPrices = new HashMap<>();

        for (String currencyCode : currencies) {
			
            Resource currencyResource = resourceResolver.getResource("/etc/currencies/" + currencyCode);
			
            if (currencyResource != null) {
                double conversionRate = currencyResource.getValueMap().get("conversionRate", Double.class);
                String currencyName = currencyResource.getValueMap().get("name", String.class);

                double convertedPrice = price * conversionRate;

                CurrencyData currencyData = new CurrencyData(currencyName, String.format("%.2f", convertedPrice));
                convertedPrices.put(currencyCode, currencyData);
            }
        }

        return convertedPrices;
    }
}
