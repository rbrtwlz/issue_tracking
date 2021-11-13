package org.rbrtwlz.issuetracking.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
