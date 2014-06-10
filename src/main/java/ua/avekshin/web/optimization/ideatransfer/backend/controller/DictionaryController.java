package ua.avekshin.web.optimization.ideatransfer.backend.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.avekshin.web.optimization.ideatransfer.backend.model.PairEntity;

import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Oleksii
 * Date: 20.12.13
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class DictionaryController {

    @RequestMapping(value = "/partTypes")
    public Collection<PairEntity> getPartTypes() {
        Collection<PairEntity> partTypes = Lists.newArrayList();
        partTypes.add(new PairEntity(1, "type#1"));
        partTypes.add(new PairEntity(2, "type#2"));
        partTypes.add(new PairEntity(3, "type#3"));
        partTypes.add(new PairEntity(4, "type#4"));
        return partTypes;
    }

    @RequestMapping(value = "/marketTypes")
    public Collection<PairEntity> getMarketTypes() {
        Collection<PairEntity> marketTypes = Lists.newArrayList();
        marketTypes.add(new PairEntity(1, "market#1"));
        marketTypes.add(new PairEntity(2, "market#2"));
        marketTypes.add(new PairEntity(3, "market#3"));
        marketTypes.add(new PairEntity(4, "market#4"));
        return marketTypes;
    }

    @RequestMapping(value = "/ideaStages")
    public Collection<PairEntity> getIdeaStages() {
        Collection<PairEntity> ideaStages = Lists.newArrayList();
        ideaStages.add(new PairEntity(1, "stage#1"));
        ideaStages.add(new PairEntity(2, "stage#2"));
        ideaStages.add(new PairEntity(3, "stage#3"));
        ideaStages.add(new PairEntity(4, "stage#4"));
        return ideaStages;
    }

}
