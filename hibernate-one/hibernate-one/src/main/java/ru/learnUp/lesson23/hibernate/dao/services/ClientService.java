package ru.learnUp.lesson23.hibernate.dao.services;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnUp.lesson23.hibernate.dao.entity.Client;
import ru.learnUp.lesson23.hibernate.dao.filters.ClientFilter;
import ru.learnUp.lesson23.hibernate.dao.repository.ClientRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.learnUp.lesson23.hibernate.dao.specifications.ClientSpecification.byFilter;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.getById(id);
    }

    public List<Client> getClientBy(ClientFilter filter) {
        Specification<Client> specification = where(byFilter(filter));
        return clientRepository.findAll(specification);
    }

    @Transactional

    public Client update(Client client) {
        return clientRepository.save(client);
    }

    public Boolean delete(Long id) {
        clientRepository.delete(clientRepository.getById(id));
        return true;
    }
}
