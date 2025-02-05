package dev.andreafay.my_copy_paste.service.impl;

import dev.andreafay.my_copy_paste.model.Link;
import dev.andreafay.my_copy_paste.model.User;
import dev.andreafay.my_copy_paste.repository.LinkRepository;
import dev.andreafay.my_copy_paste.repository.UserRepository;
import dev.andreafay.my_copy_paste.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LinkRepository linkRepository;

    @Override
    public String addLinkToUser(String email, String linkName, String linkUrl) {
        Optional<User> optionalUser = userRepository.findById(email);
        if(optionalUser.isEmpty()){
            return "User not found";
        }

        User user = optionalUser.get();

        Link newLink = new Link();
        newLink.setName(linkName);
        newLink.setUrl(linkUrl);
        newLink.setUser(user);

        linkRepository.save(newLink);
        return "Link added successfully!";
    }

    @Override
    public List<Link> getUserLinksByEmail(String email) {
        Optional<User> optionalUser = userRepository.findById(email);
        if(optionalUser.isEmpty()){
            System.out.println("User not found");
            return null;
        }

        User user = optionalUser.get();

        return linkRepository.findByUserEmail(user.getEmail());
    }

    @Override
    public boolean deleteLink(long id) {
        if (linkRepository.existsById(id)) {
            linkRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean editLink(long id, String name, String url) {
        Optional<Link> optionalLink = linkRepository.findById(id);
        if (optionalLink.isPresent()) {
            Link link = optionalLink.get();
            if(name != null && !name.isEmpty()){
                link.setName(name);
            }
            if(url != null && !url.isEmpty()){
                link.setUrl(url);
            }
            linkRepository.save(link);
            return true;
        }
        return false;
    }

}