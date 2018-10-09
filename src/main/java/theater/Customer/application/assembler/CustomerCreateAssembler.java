package theater.Customer.application.assembler;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import theater.Customer.application.dto.CustomerCreateDto;
import theater.Customer.application.dto.CustomerInListDto;
import theater.Customer.domain.entity.Customer;
import theater.common.infrastructure.persistence.hibernate.UnitOfWorkHibernate;
import java.util.List;
import org.modelmapper.TypeToken;

@Component
public class CustomerCreateAssembler {
	@Autowired
	protected UnitOfWorkHibernate unitOfWork;
	
	public Customer toEntity(CustomerCreateDto customerCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		Customer customer = modelMapper.map(customerCreateDto, Customer.class);
		return customer;
	}
        
   public List<Customer> toDtoList(List<CustomerInListDto> customerInListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<Customer> customer = modelMapper.map(customerInListDto, new TypeToken<List<Customer>>() {}.getType());
		return customer;
	}
   
   public List<CustomerInListDto> toDtoList2(List<Customer> customerInListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<CustomerInListDto> bankAccountListDto = modelMapper.map(customerInListDto, new TypeToken<List<CustomerInListDto>>() {}.getType());
		return bankAccountListDto;
	}
      
	
	private Converter<CustomerCreateDto, Customer> getConverter() {
		Converter<CustomerCreateDto, Customer> converter = new Converter<CustomerCreateDto, Customer>() {
		    @Override
		    public Customer convert(MappingContext<CustomerCreateDto, Customer> context) {
		    	CustomerCreateDto customerCreateDto =  CustomerCreateDto.class.cast(context.getSource());
		    	//MoneyAbstraction balance = new Money(bankAccountCreateDto.getBalance(), bankAccountCreateDto.getCurrency());
		    	Customer customer = new Customer();
		        customer.setId(customerCreateDto.getId());
		        customer.setName(customerCreateDto.getName());
		        customer.setEmail(customerCreateDto.getEmail());
		        customer.setStatus(customerCreateDto.getStatus());
		        customer.setStatusExpirationDate(customerCreateDto.getStatusExpirationDate());	
		        customer.setMoneySpent(customerCreateDto.getMoneySpent());		       	        
		    	return customer;
		    }
		};
		return converter;
	}
	
	public CustomerCreateDto toDto(Customer customer) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Customer, CustomerCreateDto> map = new PropertyMap<Customer, CustomerCreateDto>() {
		  protected void configure() {
			map().setId(source.getId());
			map().setName(source.getName());
		    map().setEmail(source.getEmail());  
		    map().setStatus(source.getStatus());
		    map().setStatusExpirationDate(source.getStatusExpirationDate());
		    map().setMoneySpent(source.getMoneySpent());
		  
		  }
		};
		
		modelMapper.addMappings(map);
		CustomerCreateDto customerCreateDto = modelMapper.map(customer, CustomerCreateDto.class);
		return customerCreateDto;
	}
		
}
