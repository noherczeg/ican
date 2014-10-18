package hu.noherczeg.ican.countries;

import hu.noherczeg.ican.countries.io.Country;
import hu.noherczeg.ican.countries.io.GetCountryRequest;
import hu.noherczeg.ican.countries.io.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * ican
 * Created by noherczeg on 2014-10-18.
 */
@Endpoint
class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://norbertherczeg.me/projects/ican";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        Country country = countryRepository.findCountry(request.getName());
        if (country == null) {
            throw new MemberDetailsFault();
        }
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }

    @SoapFault(faultCode = FaultCode.SERVER, customFaultCode = "001", faultStringOrReason = "No Country found")
    public class MemberDetailsFault extends RuntimeException{
    }
}
