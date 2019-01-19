package service;

import model.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.CommodityRepository;

import javax.transaction.Transactional;

/**
 * @ClassName: ProductService
 * @Description: TODO
 * @Author: ycqian
 * @Date: 2018/12/31
 * @Version: 0.0.1
 */
@Component
@Transactional
public class ProductService {
//    private static ProductService service = new ProductService();

    @Autowired
    private CommodityRepository repository = new CommodityRepository();

//    private ProductService() {
//
//    }

//    public static ProductService getInstance() {
//        return service;
//    }

    public ProductList getProductList(int page,int size) {
        int maxPage = repository.getPages(size);
        page = page > 0 ? page : 1;
        page = page <= maxPage ? page : maxPage;

        ProductList list = new ProductList(repository.getCommodities(page, size));
        return list;
    }

    public int getMaxPage(int size) {
        return repository.getPages(size);
    }
}
