
package cn.unowen.vo;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Scope("prototype")
@Component
public class PasswordForm {
	private String name;
	@NotNull
	private String oldPassword;
	@NotNull
	private String newePassword;

}
