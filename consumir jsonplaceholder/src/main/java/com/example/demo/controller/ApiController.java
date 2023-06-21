package com.example.demo.controller;

import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ApiController {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //METODO SIMPLE
//    @GetMapping("/posts")
//    public Post[] getPosts() {
//        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
//        return restTemplate.getForObject(apiUrl, Post[].class);
//    }

    //METODO MAS COMPLETO
//    @GetMapping("/posts")
//    public ResponseEntity<List<Post>> getPosts() {
//        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
//        Post[] posts = restTemplate.getForObject(apiUrl, Post[].class);
//
//        if (posts != null && posts.length > 0) {
//            List<Post> postList = Arrays.asList(posts);
//            return ResponseEntity.ok().body(postList);
//        } else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//    }

    //CON PAGINACION 10 primeros
//    @GetMapping("/posts")
//    public ResponseEntity<List<Post>> getPosts(@RequestParam(defaultValue = "0") int page,
//                                               @RequestParam(defaultValue = "10") int size) {
//        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
//        PageRequest pageRequest = PageRequest.of(page, size);
//        ResponseEntity<Post[]> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null,
//                Post[].class);
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            Post[] posts = responseEntity.getBody();
//            List<Post> postList = Arrays.asList(posts);
//
//            if (!postList.isEmpty()) {
//                int start = (int) pageRequest.getOffset();
//                int end = Math.min((start + pageRequest.getPageSize()), postList.size());
//                List<Post> paginatedList = postList.subList(start, end);
//
//                return ResponseEntity.ok()
//                        .header("X-Total-Count", String.valueOf(postList.size()))
//                        .body(paginatedList);
//            } else {
//                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    //paginacion informacion
    //mostrar informacion en paginacion http://localhost:8081/app/api/posts?page=2&size=10
//    @GetMapping("/posts")
//    public ResponseEntity<List<Post>> getPosts(@RequestParam(defaultValue = "0") int page,
//                                               @RequestParam(defaultValue = "10") int size) {
//        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
//        PageRequest pageRequest = PageRequest.of(page, size);
//        ResponseEntity<Post[]> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null,
//                Post[].class);
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            Post[] posts = responseEntity.getBody();
//            List<Post> postList = Arrays.asList(posts);
//
//            if (!postList.isEmpty()) {
//                int start = (int) pageRequest.getOffset();
//                int end = Math.min((start + pageRequest.getPageSize()), postList.size());
//                List<Post> paginatedList = postList.subList(start, end);
//
//                return ResponseEntity.ok()
//                        .header("X-Total-Count", String.valueOf(postList.size()))
//                        .body(paginatedList);
//            } else {
//                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

//    @GetMapping("/posts")
//    public String getPosts(Model model) {
//        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
//        ResponseEntity<Post[]> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Post[].class);
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            Post[] posts = responseEntity.getBody();
//            model.addAttribute("posts", Arrays.asList(posts));
//        }
//
//        return "posts";
//    }

    @RequestMapping("/posts")
    public String getPosts(Model model) {
        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<Post[]> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Post[].class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Post[] posts = responseEntity.getBody();
            model.addAttribute("posts", Arrays.asList(posts));
        }

        return "posts";
    }

    //FORMATO JSON
    @GetMapping("/posts/json")
    public List<Post> getPostsJson() {
        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
        Post[] posts = restTemplate.getForObject(apiUrl, Post[].class);

        if (posts != null) {
            return Arrays.asList(posts);
        } else {
            return null;
        }
    }
//    @GetMapping("/posts/{id}")
//    public ResponseEntity<Post> getPostById(@PathVariable int id) {
//        String apiUrl = "https://jsonplaceholder.typicode.com/posts/" + id;
//        Post post = restTemplate.getForObject(apiUrl, Post.class);
//
//        if (post != null) {
//            return ResponseEntity.ok().body(post);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/posts/{id}")
//    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody Post updatedPost) {
//        String apiUrl = "https://jsonplaceholder.typicode.com/posts/" + id;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Post> requestEntity = new HttpEntity<>(updatedPost, headers);
//
//        try {
//            restTemplate.exchange(apiUrl, HttpMethod.PUT, requestEntity, Void.class);
//            return ResponseEntity.ok().build();
//        } catch (HttpClientErrorException.NotFound e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DeleteMapping("/posts/{id}")
//    public ResponseEntity<Void> deletePost(@PathVariable int id) {
//        String apiUrl = "https://jsonplaceholder.typicode.com/posts/" + id;
//
//        try {
//            restTemplate.delete(apiUrl);
//            return ResponseEntity.noContent().build();
//        } catch (HttpClientErrorException.NotFound e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

}






