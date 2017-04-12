package sensesnet.coffeeShop.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("sensesnet.coffeeShop.Quantity")
public class QuantityValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        double quantity = (double) value;
        if (quantity < 100.0) {
            throw new ValidatorException(new FacesMessage("Quantity cannot be low than 100 gram"));
        }
    }
}
