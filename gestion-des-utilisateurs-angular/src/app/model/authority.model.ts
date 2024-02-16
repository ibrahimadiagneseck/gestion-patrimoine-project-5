
export class Authority {

  public authorityId: number;
  public code: string;
  public name: string;

  constructor(
    authorityId = 0,
    code = '',
    name = ''
  ) {
    this.authorityId = authorityId;
    this.code = code;
    this.name = name;
  }

}
