package dev.andreafay.my_copy_paste.service;

import dev.andreafay.my_copy_paste.model.Link;

import java.util.List;

public interface LinkService {
    String addLinkToUser(String email, String linkName, String linkUrl);
    List<Link> getUserLinksByEmail(String email);
}
