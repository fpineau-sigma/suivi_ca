import {AbstractControl, ValidatorFn} from '@angular/forms';
import {Exercice} from 'app/core/model/metier/exercice.model';
import {Agence} from 'app/core/model/metier/agence.model';

export const uniqueStringValidator = (array: Agence[] | Exercice[]): ValidatorFn => {
  return (control: AbstractControl): { [key: string]: boolean } | null => {
    if (control.value != null) {
      const find = array.find(element => element.libelle === control.value);
      return find == null ? null : {'uniqueString': true};
    }
    return null;
  }
}
