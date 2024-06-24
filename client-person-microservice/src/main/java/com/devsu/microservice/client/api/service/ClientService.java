package com.devsu.microservice.client.api.service;

import com.devsu.microservice.client.api.entity.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {
    Client createClient(Client client);
    Client getClientById(Long id);
    List<Client> getAllClients();
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);
    Client patchClient(Long id, Map<String, Object> updates);
}
