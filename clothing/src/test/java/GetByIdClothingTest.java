import com.hiberus.clothing.application.services.GetByIdService;
import com.hiberus.clothing.domain.model.Garment;
import com.hiberus.clothing.domain.ports.out.GetGarmentPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetByIdClothingTest {


    @Mock
    private GetGarmentPort getGarmentPort;

    @InjectMocks
    private GetByIdService getByIdService;

    @Test
    @DisplayName("TEST_01: Get clothing by id")
    public void getClothingById(){
        String id = "REF-12345-678";
        Garment garment = new Garment(id,"Vaqueros","Ropa1","Descripcion de ropa1",20.0f);


        BDDMockito.given(getGarmentPort.getGarment(eq(id))).willReturn(garment);

        Garment result = getByIdService.getGarment(id);

        assertEquals(result,garment);
        verify(getGarmentPort,times(1)).getGarment(id);
    }

}
