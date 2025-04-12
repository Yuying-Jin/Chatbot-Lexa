package Chatbot.CustomADT;

import java.util.Arrays;

import application.Recipe;

public class ArrayPriorityQueue implements QueueInterface<Recipe>{
    private Recipe[] queue;
    private int size;

    public ArrayPriorityQueue(int capacity) {
        queue = new Recipe[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Insert the recipe to queue
    public void add(Recipe recipe) {
        if (size < queue.length) {
            queue[size] = recipe;
            size++;
//            System.out.println("Inserted recipe: " + recipe.getName());
//            System.out.println("*************" + queue.length);
            sortQueue();
        } else if (size == queue.length) {
        	resize();
        	queue[size] = recipe;
            size++;
            sortQueue();
        }
    }

    private void resize() {
    	queue = Arrays.copyOf(queue, queue.length*2);
	}


    // Update recipe's favorite and re-sort
    public void updateFavorite(Recipe recipe) {
        System.out.println("Updating favorite for: " + recipe.getName() + " with count: " + recipe.getFavorite());
        for (int i = 0; i < size; i++) {
            if (queue[i].equals(recipe)) {
                System.out.println("Found recipe at position: " + i);
                queue[i].setFavorite(recipe.getFavorite());
                sortQueue();
                displayQueue();
                break;
            }
        }
    }

    private void sortQueue() {
        mergeSort(queue, 0, size - 1);  // sort all elements of queue
    }

    private void mergeSort(Recipe[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    // 合併兩個已排序的部分
    private void merge(Recipe[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Recipe[] leftArray = new Recipe[n1];
        Recipe[] rightArray = new Recipe[n2];

        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getFavorite() >= rightArray[j].getFavorite()) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // 將剩餘的元素拷貝回陣列
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // display sorted recipes
    public void displayQueue() {
        if (size == 0) {
            System.out.println("Queue is empty!");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(queue[i].getName() + " - Favorites: " + queue[i].getFavorite());
            }
        }
    }
    
    public Recipe[] getTopThreeRecipes() {
        int limit = Math.min(3, size);
        Recipe[] topThree = new Recipe[limit];
 
        for (int i = 0; i < limit; i++) {
            topThree[i] = queue[i];
            System.out.println("Top recipe: " + topThree[i].getName());
        }      
        return topThree;
    }

	@Override
	public Recipe remove() {
		if (isEmpty()) {
			return null;
		}
		
		Recipe removedRecipe = queue[0];
		System.arraycopy(queue, 1, queue, 0, size - 1);
		size--;
		return removedRecipe;
	}
}
