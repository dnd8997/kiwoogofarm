import { localaxios } from "./BaseAxios";

const local = localaxios()

async function searchRecipes(value, success, fail) {
    await local.post('/recipe/all', JSON.stringify(value))
        .then(success)
        .catch(fail)
}

async function getRecipeById(id, success, fail) {
    await local.get(`/recipe/${id}`)
        .then(success)
        .catch(fail)
}

async function likeRecipe(value, success, fail) {
    await local.post(`/recipe/like`, JSON.stringify(value))
        .then(success)
        .catch(fail)
}

export {
    searchRecipes,
    getRecipeById,
    likeRecipe,
}