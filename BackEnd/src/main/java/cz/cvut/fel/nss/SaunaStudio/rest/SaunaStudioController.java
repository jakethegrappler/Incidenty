package cz.cvut.fel.nss.SaunaStudio.rest;

import cz.cvut.fel.nss.SaunaStudio.bo.SaunaStudioBO;
import cz.cvut.fel.nss.SaunaStudio.dto.SaunaStudioDTO;
import cz.cvut.fel.nss.SaunaStudio.mapper.SaunaStudioMapper;
import cz.cvut.fel.nss.SaunaStudio.service.SaunaStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * REST kontroler pro operace spojené se saunovými studii.
 * <p>
 * Tento kontroler poskytuje koncové body pro získávání všech saunových studií
 * a vytváření nových saunových studií.
 * </p>
 */
@RestController
@RequestMapping(value = "/studio")
public class SaunaStudioController {

    private final SaunaStudioService saunastudioservice;
    private final SaunaStudioMapper saunaStudioMapper;

    /**
     * Konstruktor {@link SaunaStudioController} s uvedenými službami a mapery.
     *
     * @param saunastudioservice Služba pro správu saunových studií
     * @param saunaStudioMapper Maper pro převod {@link SaunaStudioDTO} na {@link SaunaStudioBO}
     */
    @Autowired
    public SaunaStudioController(SaunaStudioService saunastudioservice, SaunaStudioMapper saunaStudioMapper) {
        this.saunastudioservice = saunastudioservice;
        this.saunaStudioMapper = saunaStudioMapper;
    }

    /**
     * Získává všechna saunová studia.
     *
     * <p>
     * Tento koncový bod vrací seznam všech saunových studií.
     * </p>
     *
     * @return Seznam {@link SaunaStudio} entit
     */
    @GetMapping()
    public List<SaunaStudio> getSaunaStudios(){
        return saunastudioservice.findAll();
    }

    /**
     * Vytváří nové saunové studio.
     *
     * <p>
     * Tento koncový bod vytvoří nové saunové studio na základě poskytnutého {@link SaunaStudioDTO}.
     * Přístup je omezen pouze na administrátory.
     * </p>
     *
     * @param saunaStudioDTO Data transfer objekt obsahující údaje o saunovém studiu
     * @return Vytvořené {@link SaunaStudio} entita
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SaunaStudio create(@RequestBody SaunaStudioDTO saunaStudioDTO){
        Objects.requireNonNull(saunaStudioDTO);
        return saunastudioservice.create(saunaStudioMapper.saunaStudioDtoToSaunaStudioBo(saunaStudioDTO));
    }

}
