package at.wiencampus.se.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServiceReply implements Serializable {
    private CustomerServiceName name;
    private CustomerData customerData;
    private List<CustomerData> customers;
    private Exception exception;
}