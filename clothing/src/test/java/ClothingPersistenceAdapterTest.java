import com.hiberus.clothing.domain.model.Garment;
import com.hiberus.clothing.infrastructure.adapter.out.persistence.GarmentEntity;
import com.hiberus.clothing.infrastructure.adapter.out.persistence.GarmentPersistenceAdapter;
import com.hiberus.clothing.infrastructure.adapter.out.persistence.GarmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClothingPersistenceAdapterTest {


    @Mock
    private GarmentRepository garmentRepository;

    @InjectMocks
    private GarmentPersistenceAdapter garmentPersistenceAdapter;


    @Test
    @DisplayName("TEST 1: Get garment")
    public void getGarment(){
        String id = "REF-12345-678";
        String family = "Vaqueros";
        GarmentEntity garmentEntity = new GarmentEntity(id,family,"Ropa1","Descripcion de ropa1",20.0f);

        BDDMockito.given(garmentRepository.getById(eq(id))).willReturn(garmentEntity);

        Garment result = garmentPersistenceAdapter.getGarment(id);


        assertNotNull(result);
        verify(garmentRepository,times(1)).getById(anyString());
    }
    @Test
    @DisplayName("TEST 1.1: Get garment null")
    public void getGarmentNull(){
        String id = "REF-12345-678";
        String family = "Vaqueros";

        BDDMockito.given(garmentRepository.getById(eq(id))).willReturn(null);

        Garment result = garmentPersistenceAdapter.getGarment(id);


        assertNull(result);
        verify(garmentRepository,times(1)).getById(anyString());
    }

    @Test
    @DisplayName("TEST 2: Get clothing by family")
    public void getClothingByFamily(){
        String id = "REF-12345-678";
        String id2 = "REF-12345-679";
        String family = "Vaqueros";
        GarmentEntity garment = new GarmentEntity(id,family,"Ropa1","Descripcion de ropa1",20.0f);
        GarmentEntity garment2 = new GarmentEntity(id2,family,"Ropa2","Descripcion de ropa2",25.0f);
        List<GarmentEntity> list = new ArrayList<>(Arrays.asList(garment,garment2));


        BDDMockito.given(garmentRepository.getByFamily(eq(family))).willReturn(list);

        List<Garment> result = garmentPersistenceAdapter.getClothingByFamily(family);


        assertNotNull(result);
        assertEquals(result.size(),2);
        verify(garmentRepository,times(1)).getByFamily(anyString());
    }

    @Test
    @DisplayName("TEST 3: Get all")
    public void getAll(){
        String id = "REF-12345-678";
        String id2 = "REF-12345-679";
        String family = "Vaqueros";
        GarmentEntity garment = new GarmentEntity(id,family,"Ropa1","Descripcion de ropa1",20.0f);
        GarmentEntity garment2 = new GarmentEntity(id2,family,"Ropa2","Descripcion de ropa2",25.0f);
        List<GarmentEntity> list = new ArrayList<>(Arrays.asList(garment,garment2));


        BDDMockito.given(garmentRepository.findAll()).willReturn(list);

        List<Garment> result = garmentPersistenceAdapter.getAll();


        assertNotNull(result);
        assertEquals(result.size(),2);
        verify(garmentRepository,times(1)).findAll();
    }
}
