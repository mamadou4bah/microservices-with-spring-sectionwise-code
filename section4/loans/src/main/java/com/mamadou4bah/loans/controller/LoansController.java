/**
 * 
 */
package com.mamadou4bah.loans.controller;

import java.util.List;

import com.mamadou4bah.loans.model.Loans;
import com.mamadou4bah.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mamadou4bah.loans.model.Customer;

/**
 * @author mamadou4bah
 *
 */

@RestController
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;

	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		if (loans != null) {
			return loans;
		} else {
			return null;
		}

	}

}
