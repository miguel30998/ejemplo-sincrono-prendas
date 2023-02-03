import com.hiberus.clothing.application.services.GetByFamilyService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetByFamilyTest {

    @Mock
    private GetGarmentPort getGarmentPort;

    @InjectMocks
    private GetByFamilyService getByFamilyService;

    @Test
    @DisplayName("TEST_01: Get clothing by family")
    public void getClothingById(){
        String id = "REF-12345-678";
        String id2 = "REF-12345-679";
        String family = "Vaqueros";
        Garment garment = new Garment(id,family,"Ropa1","Descripcion de ropa1",20.0f);
        Garment garment2 = new Garment(id2,family,"Ropa2","Descripcion de ropa2",25.0f);
        List<Garment> list = new ArrayList<>(Arrays.asList(garment,garment2));

        BDDMockito.given(getGarmentPort.getClothingByFamily(eq(family))).willReturn(list);

        List<Garment> result = getByFamilyService.getClothing(family);

        assertEquals(result,list);
        verify(getGarmentPort,times(1)).getClothingByFamily(family);
    }

    @Test
    @DisplayName("TEST_02: Get clothing by family empty list")
    public void getClothingByIdEmptyList(){
        String family = "Vaqueros";
        List<Garment> list = new ArrayList<>();

        BDDMockito.given(getGarmentPort.getClothingByFamily(eq(family))).willReturn(list);

        List<Garment> result = getByFamilyService.getClothing(family);

        assertEquals(result,list);
        verify(getGarmentPort,times(1)).getClothingByFamily(family);
    }
}
