package com.ecomv2.shippinservice.ShippingService;

import com.ecomv2.shippinservice.Model.Shipping;
import com.ecomv2.shippinservice.Repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements ShippingService{

    @Autowired
    private ShippingRepository shippingRepository;


    @Override
    public Shipping save(Shipping shipping) {
        Shipping savedShipping = null;
        try{
            savedShipping =  shippingRepository.save(shipping);
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        if(savedShipping!=null){
            //update status
            shipping.setShippingStatus("SHIPPED");
            shippingRepository.save(savedShipping);

        }

        return savedShipping;
    }
}
