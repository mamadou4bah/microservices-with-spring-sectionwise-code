/**
 * 
 */
package com.mamadou4bah.loans.controller;

import java.util.List;

import com.mamadou4bah.loans.config.LoansServiceConfig;
import com.mamadou4bah.loans.model.Loans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mamadou4bah.loans.model.Customer;
import com.mamadou4bah.loans.model.Properties;
import com.mamadou4bah.loans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Mamadou 4 Bah
 *
 */

@RestController
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;
	
	@Autowired
    LoansServiceConfig loansConfig;

	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		System.out.println("Invoking Loans Microservice");
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		if (loans != null) {
			return loans;
		} else {
			return null;
		}

	}
	
	@GetMapping("/loans/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(),
				loansConfig.getMailDetails(), loansConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}

}
