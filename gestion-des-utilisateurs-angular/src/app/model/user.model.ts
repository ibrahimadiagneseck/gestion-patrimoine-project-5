
export class User{

  public userId: string;
  public id: number;
  public firstName: string;
  public lastName: string;
  public mobile: string;
  public email : string;
  public pwd: string;
  public role : string;
  public lastLoginDate: Date | null;
  public lastLoginDateDisplay: Date | null;
  public joinDate: Date | null;
  public profileImageUrl: string;
  public active: boolean;
  public notLocked: boolean;
  public authorities: [];
  public statusCd: string;
  public statusMsg : string;
  public authStatus : string;


  constructor(userId?: string, id?: number,firstName?: string,lastName?: string, mobile?: string, email?: string,  pwd?: string,
    lastLoginDate?: Date,lastLoginDateDisplay?: Date,joinDate?: Date,
    profileImageUrl?: string,active?: boolean,notLocked?: boolean,authorities?: [],
    role?: string,statusCd?:string,statusMsg?:string, authStatus?:string){
        this.userId = userId || '';
        this.id = id || 0;
        this.firstName = firstName || '';
        this.lastName = lastName || '';
        this.mobile = mobile || '';
        this.email = email || '';
        this.pwd = pwd || '';
        this.lastLoginDate = new Date();
        this.lastLoginDateDisplay = new Date();
        this.joinDate = new Date();
        this.profileImageUrl = profileImageUrl || '';
        this.active = active || true;
        this.notLocked = notLocked || true;
        this.authorities = [];
        this.role = role || '';
        this.statusCd = statusCd || '';
        this.statusMsg = statusMsg || '';
        this.authStatus = authStatus || '';
  }

}
