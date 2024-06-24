package com.devsu.microservice.client.api.service;

import com.devsu.microservice.client.api.entity.Client;
import com.devsu.microservice.client.api.exception.ClientNotFoundException;
import com.devsu.microservice.client.api.repository.ClientRepository;
import com.devsu.microservice.common.exception.InvalidFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        try {
            return clientRepository.save(client);
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidFieldException("El cliente ya existe");
        }
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        existingClient.copy(client);

        try {
            return clientRepository.save(existingClient);
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidFieldException("El cliente ya existe");
        }
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client patchClient(Long id, Map<String, Object> updates) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "password":
                    existingClient.setPassword((String) value);
                    break;
                case "status":
                    existingClient.setStatus((Boolean) value);
                    break;
                case "name":
                    existingClient.setName((String) value);
                    break;
                case "gender":
                    existingClient.setGender((String) value);
                    break;
                case "age":
                    existingClient.setAge((Integer) value);
                    break;
                case "identification":
                    existingClient.setIdentification((String) value);
                    break;
                case "address":
                    existingClient.setAddress((String) value);
                    break;
                case "phone":
                    existingClient.setPhone((String) value);
                    break;
                default:
                    throw new InvalidFieldException("Invalid field: " + key);
            }
        });

        try {
            return clientRepository.save(existingClient);
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidFieldException("El cliente ya existe");
        }
    }
}
