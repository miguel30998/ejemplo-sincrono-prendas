import com.hiberus.clothing.application.services.GetAll;
import com.hiberus.clothing.application.services.GetByFamilyService;
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
public class GetAllTest {

    @Mock
    private GetGarmentPort getGarmentPort;

    @InjectMocks
    private GetAll getAll;


    @Test
    @DisplayName("TEST_01: Get all the products")
    public void getAllClothing(){
        String id = "REF-12345-678";
        String id2 = "REF-12345-679";
        String family = "Vaqueros";
        String family2 = "Vaqueros2";
        Garment garment = new Garment(id,family,"Ropa1","Descripcion de ropa1",20.0f);
        Garment garment2 = new Garment(id2,family2,"Ropa2","Descripcion de ropa2",25.0f);
        List<Garment> list = new ArrayList<>(Arrays.asList(garment,garment2));

        BDDMockito.given(getGarmentPort.getAll()).willReturn(list);

        List<Garment> result = getAll.getAll();

        assertEquals(result,list);
        assertEquals(result.size(),2);
        verify(getGarmentPort,times(1)).getAll();
    }

    @Test
    @DisplayName("TEST_02: Get all the products empty list")
    public void getAllClothingEmptyList(){
        List<Garment> list = new ArrayList<>();

        BDDMockito.given(getGarmentPort.getAll()).willReturn(list);

        List<Garment> result = getAll.getAll();

        assertEquals(result,list);
        assertEquals(result.size(),0);
        verify(getGarmentPort,times(1)).getAll();
    }
}
