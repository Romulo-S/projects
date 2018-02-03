package controller;

import pojo.Node;
import application.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.NodeRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class NodeController {

    @Autowired
    private Tree tree;

    @Autowired
    NodeRepository nodeRepository;


    @PostMapping("/node")
    public long addNode(@Valid @RequestBody Node node) {
        Node savedNode = nodeRepository.save(node);
        if (savedNode.getId() == 1) {
            tree = new Tree(savedNode);
            return savedNode.getId();
        }
        tree.addChild(savedNode);
        return savedNode.getId();
    }

    @PutMapping("/node")
    @Transactional
    public long updateNode(@Valid @RequestBody Node node) {
        boolean exists = nodeRepository.exists(node.getId());

        if (exists) {
            Node savedNode = nodeRepository.save(node);
            Tree.updateNode(node);
            return savedNode.getId();
        }
        return 0;
    }

    @GetMapping ("/node")
    public Node showTree() {

       return Tree.getTree();
    }
    
    @GetMapping ("/node/{parentId}")
    public ArrayList<Node> showNode(@PathVariable Long parentId) {

       return Tree.getTree(parentId);
    }
}
