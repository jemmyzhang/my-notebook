package pers.jz.web.api.io.controller;

import org.springframework.web.bind.annotation.RestController;
import pers.jz.web.api.io.service.FileService;

import javax.annotation.Resource;

/**
 * @author Jemmy Zhang
 */
@RestController
public class FileController {

    @Resource
    FileService fileService;


}
