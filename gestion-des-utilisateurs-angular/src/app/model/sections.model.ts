import { UniteDouaniere } from "./unite-douaniere.model";


export class Sections {

  public codeSection: string;
  public libelleSection: string;

  constructor(
    codeSection = '',
    libelleSection = ''
  ) {
    this.codeSection = codeSection;
    this.libelleSection = libelleSection;
  }

}
