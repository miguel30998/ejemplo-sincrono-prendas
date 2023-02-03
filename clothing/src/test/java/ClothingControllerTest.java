import com.google.gson.Gson;
import com.hiberus.ClothingApplication;
import com.hiberus.clothing.domain.model.Garment;
import com.hiberus.clothing.domain.ports.in.GetAllUseCase;
import com.hiberus.clothing.domain.ports.in.GetByFamilyUseCase;
import com.hiberus.clothing.domain.ports.in.GetByIdUseCase;
import com.hiberus.clothing.infrastructure.adapter.in.web.controller.ClothingControllerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClothingControllerImpl.class)
@ContextConfiguration(classes={ClothingApplication.class})
public class ClothingControllerTest {


    @MockBean
    private GetByIdUseCase getByIdUseCase;
    @MockBean
    private GetByFamilyUseCase getByFamilyUseCase;
    @MockBean
    private GetAllUseCase getAllUseCase;

    MockMvc mockMvc;
    private Gson gson = new Gson();

    String ID = "REF-12345-678";
    String FAMILY = "Vaqueros";
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ClothingControllerImpl(getByIdUseCase,getByFamilyUseCase,getAllUseCase ))
                .build();
    }

    @Test
    @DisplayName("Controller Unit Test 1: get garment by id")
    public void controllerTestGetGarmentById() throws Exception {
        Garment garment = new Garment(ID,FAMILY,"Ropa1","Descripcion de ropa1",20.0f);
        BDDMockito.given(getByIdUseCase.getGarment(eq(ID))).willReturn(garment);


        MvcResult result = mockMvc.perform(get(String.format("/clothing/byId/%s",ID)))
                .andExpect(status().isOk())
                .andReturn();

        verify(getByIdUseCase,times(1)).getGarment(any());
        assertEquals(result.getResponse().getContentAsString(),gson.toJson(garment));
    }

    @Test
    @DisplayName("Controller Unit Test 1.1: get garment by id doesnt exist")
    public void controllerTestGetGarmentByIdNotFound() throws Exception {
        BDDMockito.given(getByIdUseCase.getGarment(eq(ID))).willReturn(null);


        MvcResult result = mockMvc.perform(get(String.format("/clothing/byId/%s",ID)))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(getByIdUseCase,times(1)).getGarment(any());
        assertEquals(result.getResponse().getContentAsString(),"Doesnt exist");
    }

    @Test
    @DisplayName("Controller Unit Test 1.2: get garment by id BD exception")
    public void controllerTestGetGarmentByIdBDException() throws Exception {
        BDDMockito.given(getByIdUseCase.getGarment(eq(ID))).willThrow(new IllegalStateException("Exception"));


        MvcResult result = mockMvc.perform(get(String.format("/clothing/byId/%s",ID)))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(getByIdUseCase,times(1)).getGarment(any());
        assertEquals(result.getResponse().getContentAsString(),"Exception");
    }

    @Test
    @DisplayName("Controller Unit Test 2: get garment by family")
    public void controllerTestGetGarmentByFamily() throws Exception {
        Garment garment = new Garment(ID,FAMILY,"Ropa1","Descripcion de ropa1",20.0f);
        BDDMockito.given(getByFamilyUseCase.getClothing(eq(FAMILY))).willReturn(new ArrayList<>(Arrays.asList(garment)));


        MvcResult result = mockMvc.perform(get(String.format("/clothing/byFamily/%s",FAMILY)))
                .andExpect(status().isOk())
                .andReturn();

        verify(getByFamilyUseCase,times(1)).getClothing(any());
        assertEquals(result.getResponse().getContentAsString(),gson.toJson(new ArrayList<>(Arrays.asList(garment))));
    }

    @Test
    @DisplayName("Controller Unit Test 2.1: get garment by family BD exception")
    public void controllerTestGetGarmentByFamilyBDException() throws Exception {
        Garment garment = new Garment(ID,FAMILY,"Ropa1","Descripcion de ropa1",20.0f);
        BDDMockito.given(getByFamilyUseCase.getClothing(eq(FAMILY))).willThrow(new IllegalStateException("Exception"));


        MvcResult result = mockMvc.perform(get(String.format("/clothing/byFamily/%s",FAMILY)))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(getByFamilyUseCase,times(1)).getClothing(any());
        assertEquals(result.getResponse().getContentAsString(),"Exception");
    }


    @Test
    @DisplayName("Controller Unit Test 3: get clothing")
    public void controllerTestGetClothing() throws Exception {
        Garment garment = new Garment(ID,FAMILY,"Ropa1","Descripcion de ropa1",20.0f);
        BDDMockito.given(getAllUseCase.getAll()).willReturn(new ArrayList<>(Arrays.asList(garment)));


        MvcResult result = mockMvc.perform(get(String.format("/clothing")))
                .andExpect(status().isOk())
                .andReturn();

        verify(getAllUseCase,times(1)).getAll();
        assertEquals(result.getResponse().getContentAsString(),gson.toJson(new ArrayList<>(Arrays.asList(garment))));
    }

    @Test
    @DisplayName("Controller Unit Test 3.1: get clothing bd exception")
    public void controllerTestGetClothingBDException() throws Exception {
        BDDMockito.given(getAllUseCase.getAll()).willThrow(new IllegalStateException("Exception"));


        MvcResult result = mockMvc.perform(get(String.format("/clothing")))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(getAllUseCase,times(1)).getAll();
        assertEquals(result.getResponse().getContentAsString(),"Exception");
    }
}
