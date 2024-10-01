import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Address;
import com.example.AddressController;
import com.example.AddressService;

public class AddressControllerTest {

    private AddressService addressService;
    private AddressController addressController;

    @BeforeEach
    public void setUp() {
        addressService = mock(AddressService.class);
        addressController = new AddressController(addressService);
    }

    @Test
    public void testGetAddress_ValidCep() {
        String cep = "12345678";
        Address endereco = new Address("Rua", "Cidade", "Estado");

        when(addressService.getAddressByCep(cep)).thenReturn(endereco);

        Address enderecoReturn = addressController.getAddress(cep);

        assertEquals(endereco.getStreet(), enderecoReturn.getStreet());
        assertEquals(endereco.getCity(), enderecoReturn.getCity());
        assertEquals(endereco.getState(), enderecoReturn.getState());
    }

    @Test
    public void testGetAddress_InvalidCep() {
        String cep = "1234567";

        when(addressService.getAddressByCep(cep)).thenReturn(null);

        Address enderecoReturn = addressController.getAddress(cep);

        assertNull(enderecoReturn);
    }
}

