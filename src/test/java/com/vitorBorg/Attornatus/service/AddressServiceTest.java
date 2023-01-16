package com.vitorBorg.Attornatus.service;

import com.vitorBorg.Attornatus.model.AddressModel;
import com.vitorBorg.Attornatus.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    AddressModel addressModel;
    Optional<AddressModel> addressTestOptional;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        starting_data();
    }

    private void starting_data(){
        addressModel = new AddressModel(Long.valueOf(1), "Logradouro", "000", "0", "City", Long.valueOf(1));
        addressTestOptional = Optional.of(addressModel);
    }

    @Test
    void should_be_able_to_create_a_new_Address() {
        Mockito.when(addressRepository.save(Mockito.any())).thenReturn(addressModel);
        AddressModel response = addressService.createAddress(addressModel);

        assertNotNull(response);
        assertEquals(response.getClass(), AddressModel.class);
        assertEquals(response.getIdAddress(), addressModel.getIdAddress());
        assertEquals(response.getIdPerson(), addressModel.getIdPerson());
        assertEquals(response.getCepAddress(), addressModel.getCepAddress());
        assertEquals(response.getLogradouroAddress(), addressModel.getLogradouroAddress());
        assertEquals(response.getCityAddress(), addressModel.getCityAddress());
        assertEquals(response.getNumberAddress(), addressModel.getNumberAddress());
    }

    @Test
    void getPeopleAddress() {
    }

    @Test
    void should_be_able_to_find_a_address_by_id() {
        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(addressTestOptional);
        Optional<AddressModel> response = addressService.getAddressById(Long.valueOf(1));

        assertEquals(response.isPresent(), true);
        assertEquals(response.getClass(), addressTestOptional.getClass());
        assertEquals(response.get().getIdAddress(), addressTestOptional.get().getIdAddress());
        assertEquals(response.get().getIdPerson(), addressTestOptional.get().getIdPerson());
        assertEquals(response.get().getCityAddress(), addressTestOptional.get().getCityAddress());
        assertEquals(response.get().getCepAddress(), addressTestOptional.get().getCepAddress());
        assertEquals(response.get().getLogradouroAddress(), addressTestOptional.get().getLogradouroAddress());
        assertEquals(response.get().getNumberAddress(), addressTestOptional.get().getNumberAddress());
    }

    @Test
    void should_be_able_to_delete_a_address() {
        doNothing().when(addressRepository).deleteById(Mockito.anyLong());
        addressService.deleteAddressById(Long.valueOf(1));

        verify(addressRepository, times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void should_be_able_to_update_a_address() {
        Mockito.when(addressRepository.findById(Mockito.anyLong())).thenReturn(addressTestOptional);
        Mockito.when(addressRepository.save(Mockito.any())).thenReturn(addressModel);
        AddressModel response = addressService.updateAddressById(addressModel, Long.valueOf(1));

        assertNotNull(response);
        assertEquals(response.getClass(), AddressModel.class);
        assertEquals(response.getIdAddress(), addressModel.getIdAddress());
        assertEquals(response.getIdPerson(), addressModel.getIdPerson());
        assertEquals(response.getCepAddress(), addressModel.getCepAddress());
        assertEquals(response.getLogradouroAddress(), addressModel.getLogradouroAddress());
        assertEquals(response.getCityAddress(), addressModel.getCityAddress());
        assertEquals(response.getNumberAddress(), addressModel.getNumberAddress());
    }
}