import { localaxios } from "./BaseAxios";

const local = localaxios();

async function searchRecipes(value, success, fail) {
  await local
    .post("/recipe/all", JSON.stringify(value))
    .then(success)
    .catch(fail);
}

async function getRecipeById(id, success, fail) {
  await local.get(`/recipe/${id}`).then(success).catch(fail);
}

async function likeRecipe(recipeId, email, success, fail) {
  await local
    .post(`/recipe/${recipeId}/favorites/${email}`)
    .then(success)
    .catch(fail);
}

async function recommendRecipe(value, success, fail) {
  const queryParams = new URLSearchParams({
    ingredients: value.ingredients,
    cook: value.cook,
    difficulty: value.difficulty,
  });

  await local
    .get(`/recipe/search/option?${queryParams}`)
    .then(success)
    .catch(fail);
}

async function isRecipeLiked(recipeId, email, success, fail) {
  await local
    .get(`/recipe/checkRecipeIsLiked/${recipeId}/${email}`)
    .then(success)
    .catch(fail);
}

export {
  getRecipeById,
  isRecipeLiked,
  likeRecipe,
  recommendRecipe,
  searchRecipes,
};
