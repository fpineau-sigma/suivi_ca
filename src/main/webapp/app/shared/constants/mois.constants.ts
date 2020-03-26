export enum Mois {
  JAN = 'Janvier',
  FEB = 'Fevrier',
  MAR = 'Mars',
  APR = 'Avril',
  MAY = 'Mai',
  JUN = 'Juin',
  JUL = 'Juillet',
  AUG = 'Aout',
  SEP = 'Septembre',
  OCT = 'Octobre',
  NOV = 'Novembre',
  DEC = 'Decembre'
}

export const getMoisByIndex = function (inputindex): string {
  const keys = Object.keys(Mois);
  let result = "";
  keys.forEach((obj, index) => {
    if (inputindex === index) {
      result = Mois[obj];
    }
  });
  return result;
}
