package de.hska.iwi.vislab.productcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {



    @Autowired
    ProductRepository productRepo;

    public Product[] getAllProducts(){
        List<Product> list = productRepo.findAll();
        Product[] products = new Product[list.size()];
        products = list.toArray(products);
        return products;
    }

    public Product[] getAllProducts(Object[] args){

        Optional<String> searchValue = (Optional<String>) args[0];
        Optional<String> priceMinValue = (Optional<String>) args[1];
        Optional<String> priceMaxValue = (Optional<String>) args[2];

        boolean checkSearchValue = checkArgument(0,args,searchValue);
        boolean checkPriceMinValue = checkArgument(1,args,priceMinValue);
        boolean checkPriceMaxValue = checkArgument(2,args,priceMaxValue);

        System.out.println("checkSearchValue: " + checkSearchValue);
        System.out.println("checkPriceMinValue: " + checkPriceMinValue);
        System.out.println("checkPriceMinValue: " + checkPriceMaxValue);


        List<Product> filteredProducts = new ArrayList<Product>();
        Product[] productsArray = getAllProducts();
        List<Product> allProducts = new ArrayList<>();
        for (Product p : productsArray) {
            allProducts.add(p);
        }
      
        for(int pos = 0; pos< allProducts.size(); pos++){
            Product prod = allProducts.get(pos);

            if (checkSearchValue & checkPriceMinValue & checkPriceMaxValue){
                String sv = searchValue.get();
                double priceMin = Double.valueOf(priceMinValue.get());
                double priceMax = Double.valueOf(priceMaxValue.get());
                System.out.println("searchValue: " + sv);
                System.out.println("priceMin: " + priceMin);
                System.out.println("priceMax: " + priceMax);
                if(prod.getName().toUpperCase().contains(sv.toUpperCase()))
                    if (prod.getPrice() >= priceMin)
                        if (prod.getPrice() <= priceMax)
                            filteredProducts.add(prod);
            }
            if (checkSearchValue & !checkPriceMinValue & !checkPriceMaxValue){
                String sv = searchValue.get();
                System.out.println("searchValue: " + sv);
                if(prod.getName().toUpperCase().contains(sv.toUpperCase()))
                    filteredProducts.add(prod);
            }
            if (checkSearchValue & checkPriceMinValue & !checkPriceMaxValue){
                String sv = searchValue.get();
                double priceMin = Double.valueOf(priceMinValue.get());
                System.out.println("searchValue: " + sv);
                System.out.println("priceMin: " + priceMin);
                if(prod.getName().toUpperCase().contains(sv.toUpperCase()))
                    if (prod.getPrice() >= priceMin)
                        filteredProducts.add(prod);
            }
            if (checkSearchValue & !checkPriceMinValue & checkPriceMaxValue){
                String sv = searchValue.get();
                double priceMax = Double.valueOf(priceMaxValue.get());
                System.out.println("searchValue: " + sv);
                System.out.println("priceMax: " + priceMax);
                if(prod.getName().toUpperCase().contains(sv.toUpperCase()))
                    if (prod.getPrice() <= priceMax)
                        filteredProducts.add(prod);
            }
            if (!checkSearchValue & checkPriceMinValue & !checkPriceMaxValue){
                double priceMin = Double.valueOf(priceMinValue.get());
                System.out.println("priceMin: " + priceMin);
                if (prod.getPrice() >= priceMin)
                    filteredProducts.add(prod);
            }
            if (!checkSearchValue & !checkPriceMinValue & checkPriceMaxValue) {
                double priceMax = Double.valueOf(priceMaxValue.get());
                System.out.println("priceMax: " + priceMax);
                if (prod.getPrice() <= priceMax)
                    filteredProducts.add(prod);
            }
            if (!checkSearchValue & checkPriceMinValue & checkPriceMaxValue) {
                double priceMin = Double.valueOf(priceMinValue.get());
                double priceMax = Double.valueOf(priceMinValue.get());
                System.out.println("priceMin: " + priceMin);
                System.out.println("priceMax: " + priceMax);
                if (prod.getPrice() >= priceMin & prod.getPrice() <= priceMax)
                    filteredProducts.add(prod);
            }


        }

        if(!checkSearchValue & !checkPriceMinValue & !checkPriceMaxValue){
            System.out.println("no filter set!");
            return getAllProducts();
        }

        Product[] products = filteredProducts.toArray(new Product[0]);
        //Product[] products = (Product[]) filteredProducts.toArray();
        return products;
    }

    private boolean checkArgument(int pos,Object[] args, Optional<String> opt) {
        boolean check = true;

        if(args[pos] == null)
            check = false;
        if(check == true){
            if(!opt.isPresent()){
                check = false;

            }
            if(check == true){
                if(opt.get() == ""){
                    check = false;
                }
            }
        }

        return check;
    }

    public Product getProduct(String name){
        return productRepo.findByName(name);
    }

    public Product getProduct(int id){
        return productRepo.findById(id);
    }

    public void addProduct(Product product){
        productRepo.save(product);
    }

    public void updateProduct(Product product){
        productRepo.save(product);
    }

    public long deleteAllProducts(){
        long deleted = 0;
        for(Product prod: productRepo.findAll())
            deleted += productRepo.deleteById(prod.getId());
        return deleted;
    }

    public long deleteProduct(String name){
        return productRepo.deleteByName(name);
    }

    public long deleteProduct(int id){
        return productRepo.deleteById(id);
    }
}