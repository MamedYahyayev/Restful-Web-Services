package az.maqa.project.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		String field1 = "field1";
		String field2 = "field2";

		MappingJacksonValue mapping = filtering(someBean, new FilteringParameter(field1, field2, null));

		return mapping;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveSomeListOfBean() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6"));

		String field2 = "field2";
		String field3 = "field3";

		MappingJacksonValue mapping = filtering(list, new FilteringParameter(null, field2, field3));

		return mapping;
	}

	private MappingJacksonValue filtering(Object someBean, FilteringParameter parameterObject) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(parameterObject.field1,
				parameterObject.field2, parameterObject.field3);

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}

}
