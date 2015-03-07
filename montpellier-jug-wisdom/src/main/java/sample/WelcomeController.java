/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2013 - 2014 Wisdom Framework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package sample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.News;

import org.apache.felix.ipojo.annotations.Requires;
import org.jug.montpellier.core.api.CartridgeConsumer;
import org.jug.montpellier.core.api.CartridgeService;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

/**
 * Your first Wisdom Controller.
 */
@Controller
public class WelcomeController extends DefaultController {

    @Requires
    CartridgeConsumer cartridgeConsumer;

    /**
     * Injects a template named 'welcome'.
     */
    @View("welcome")
    Template welcome;
    
    /**
     * The action method returning the welcome page. It handles
     * HTTP GET request on the "/" URL.
     *
     * @return the welcome page
     */
    @Route(method = HttpMethod.GET, uri = "/")
    public Result welcome() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("welcome", "Welcome to Wisdom Framework!");
        parameters.put("cartridges", cartridgeConsumer.cartridges());
        return ok(render(welcome, parameters));
    }
    
    /**
     * NEWS PART
     */
    
    @View("news")
    Template news;
    
    List<News> buildNews() {
        return Arrays.asList(
            new News("Nouveau site", "Trop bien ;)"),
            new News()
        );
    }
    
    @Route(method = HttpMethod.GET, uri = "/news")
    public Result news() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("news", buildNews());
        parameters.put("cartridges", cartridgeConsumer.cartridges());
        return ok(render(news, parameters));
    }

}
