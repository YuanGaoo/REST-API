package restAPI.REST_API;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat ;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize ;


import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

// Matcher 

public class TestWithHamcrest {

  @Test
  public void test1() {
    
    assertThat(4,equalTo(4) )  ; 
    
    assertThat("abc", is("abc") );
    
    assertThat("abc", not( equalTo("abcd")  ) );
    
    assertThat("abc", containsString("a") );
    
    assertThat("aBC", equalToIgnoringCase("abc") );

    
    assertThat(5, greaterThan(4) );
    assertThat(5, lessThan(6) );
    assertThat(5, lessThanOrEqualTo(4) );
    
    
    
    List<Integer> lst = Arrays.asList(2,3,4,5);
    
    //import static org.hamcrest.collection.IsCollectionWithSize.hasSize ;
    assertThat(lst, hasSize(4) );
    
    assertThat(lst, contains(2,3,4) );
    
    assertThat(lst, everyItem( greaterThan(1) ) );
    
    
    
    
    
    
    
    
  }
  
}