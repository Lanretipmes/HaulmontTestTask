package test_task.polyclinic.services;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_task.polyclinic.domain.Recipe;
import test_task.polyclinic.repos.RecipeRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RecipeService {

    private RecipeRepo recipeRepo;

    public RecipeService(RecipeRepo recipeRepo){
        this.recipeRepo = recipeRepo;
    }

    public void save(Recipe recipe){
        if (recipe != null)
            recipeRepo.save(recipe);
    }

    @Transactional
    public void delete(Recipe recipe){

        Hibernate.initialize(recipe.getPatient());
        Hibernate.initialize(recipe.getDoctor());
        recipeRepo.delete(recipe);

    }

    @Transactional
    public void update(long id, Recipe newRecipe){
        Recipe recipe = recipeRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        recipe.setDescription(newRecipe.getDescription());
        recipe.setDoctor(newRecipe.getDoctor());
        recipe.setPatient(newRecipe.getPatient());
        recipe.setPriority(newRecipe.getPriority());
        recipe.setCreationDate(newRecipe.getCreationDate());
        recipe.setValidity(newRecipe.getValidity());
    }

    @Transactional
    public List<Recipe> findAll(){
        List<Recipe> out;
        out = recipeRepo.findAll();
        out.forEach(recipe -> Hibernate.initialize(recipe.getDoctor()));
        out.forEach(recipe -> Hibernate.initialize(recipe.getPatient()));
        return out;
    }
}
