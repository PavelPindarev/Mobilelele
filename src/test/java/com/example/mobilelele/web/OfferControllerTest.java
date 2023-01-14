package com.example.mobilelele.web;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import com.example.mobilelele.exception.NotFoundObjectException;
import com.example.mobilelele.service.BrandService;
import com.example.mobilelele.service.OfferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OfferController.class})
@ExtendWith(SpringExtension.class)
class OfferControllerTest {
    @MockBean
    private BrandService brandService;

    @Autowired
    private OfferController offerController;

    @MockBean
    private OfferService offerService;

    /**
     * Method under test: {@link OfferController#deleteOffer(Long)}
     */
    @Test
    void testDeleteOffer() throws Exception {
        doNothing().when(offerService).deleteOffer((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/offers/{id}", 123L);
        MockMvcBuilders.standaloneSetup(offerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/all"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/offers/all"));
    }

    /**
     * Method under test: {@link OfferController#deleteOffer(Long)}
     */
    @Test
    void testDeleteOffer2() throws Exception {
        doThrow(new NotFoundObjectException("An error occurred")).when(offerService).deleteOffer((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/offers/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(offerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link OfferController#deleteOffer(Long)}
     */
    @Test
    void testDeleteOffer3() throws Exception {
        doNothing().when(offerService).deleteOffer((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/offers/{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(offerController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/all"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/offers/all"));
    }
}

