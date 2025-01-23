package com.aviva.core.servlets;

import com.aviva.core.service.CurrencyService;
import org.apache.http.entity.ContentType;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.annotation.Nonnull;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Arrays;

@Component(service = { Servlet.class })
@SlingServletPaths({"/bin/aviva/currencyconversion"})
@ServiceDescription("Currency Conversion Servlet")
public class CurrencyConversionServlet extends SlingAllMethodsServlet {

    @Reference
    private CurrencyService currencyService;

    @Override
    protected void doGet(@Nonnull final SlingHttpServletRequest request,
                         @Nonnull final SlingHttpServletResponse response) throws IOException {
      
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        response.setCharacterEncoding("UTF-8");

        double price = Double.parseDouble(request.getParameter("price"));
        String[] currencies = request.getParameter("currencies").split(",");
        String jsonResponse = currencyService.convertPrice(price, Arrays.asList(currencies)).toString();
        response.getWriter().write(jsonResponse);
    }
}
