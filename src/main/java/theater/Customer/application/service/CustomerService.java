
package theater.Customer.application.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import theater.Customer.application.assembler.CustomerCreateAssembler;
import theater.Customer.application.dto.CustomerCreateDto;
import theater.Customer.application.dto.CustomerInListDto;
import theater.Customer.application.validation.CustomerCreateValidation;
import theater.Customer.domain.entity.Customer;
import theater.Customer.domain.repository.CustomerRepository;
import theater.Customer.infrastructure.persistence.hibernate.CustomerHibernateRepository;
import theater.common.application.UnitOfWork;

@Service
public class CustomerService {
    @Autowired
    UnitOfWork unitOfWork;
    
    @Autowired
    CustomerHibernateRepository customerHibernateRepository;
	
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    CustomerCreateValidation customerCreateValidation;
    
    @Autowired
    CustomerCreateAssembler customerCreateAssembler;
    
    public List<CustomerInListDto> getAll(){
        return customerCreateAssembler.toDtoList2(customerHibernateRepository.getAll());
    }
    
    public CustomerCreateDto create(CustomerCreateDto customerCreateDto){     	
        boolean status = false;
        customerCreateDto.setStatusExpirationDate(FechaHora());
        customerCreateValidation.validate(customerCreateDto);
        Customer customer = customerCreateAssembler.toEntity(customerCreateDto);
        status = unitOfWork.beginTransaction();
        customerHibernateRepository.save(customer);
        unitOfWork.commit(status);
        CustomerCreateDto responseCustomerDto = customerCreateAssembler.toDto(customer);
        return responseCustomerDto;
    }
    
    private Date FechaHora(){		
		Date utilDate = new Date(); 
		long lnMilisegundos = utilDate.getTime();
		Timestamp sqlTimestamp = new Timestamp(lnMilisegundos);			
		return sqlTimestamp;
	}
}
