import { Section } from "jspdf-autotable";
import { CorpsAgent } from "./corps-agent.model";
import { UniteDouaniere } from "./unite-douaniere.model";
import { Sections } from "./sections.model";
import { MyDate } from "./date.model";
import { Authority } from "./authority.model";

export class User{

  public utilisateurId: number;
  public userId: string;
  public codeUniteDouaniere: UniteDouaniere;
  public codeCorpsAgent: CorpsAgent;
  public codeSection: Sections;
  public firstName: string;
  public lastName: string;
  public mobile: string;
  public email : string;
  public matriculeAgent: string;
  public codeAgent : string;
  public pwd: string;
  public role : string;
  public lastLoginDate: MyDate;
  public lastLoginDateDisplay: MyDate;
  public joinDate: MyDate;
  public profileImageUrl: string;
  public active: boolean;
  public notLocked: boolean;
  public authorities: Authority[];
  public statusCd: string;
  public statusMsg : string;
  public authStatus : string;


  constructor(userId?: string, utilisateurId?: number, codeUniteDouaniere?: UniteDouaniere, codeCorpsAgent?: CorpsAgent, codeSection?: Sections,firstName?: string,lastName?: string, mobile?: string, email?: string, matriculeAgent?: string, codeAgent?: string,  pwd?: string,
    lastLoginDate?: MyDate,lastLoginDateDisplay?: MyDate,joinDate?: MyDate,
    profileImageUrl?: string,active?: boolean,notLocked?: boolean,authorities?: [],
    role?: string,statusCd?:string,statusMsg?:string, authStatus?:string) {

        this.userId = userId || '';
        this.utilisateurId = utilisateurId || 0;
        this.codeUniteDouaniere = codeUniteDouaniere || new UniteDouaniere();
        this.codeCorpsAgent = codeCorpsAgent || new CorpsAgent();
        this.codeSection = codeSection || new Sections();
        this.firstName = firstName || '';
        this.lastName = lastName || '';
        this.mobile = mobile || '';
        this.matriculeAgent = matriculeAgent || '';
        this.email = email || '';
        this.codeAgent = codeAgent || '';
        this.pwd = pwd || '';
        this.lastLoginDate = new MyDate();
        this.lastLoginDateDisplay = new MyDate();
        this.joinDate = new MyDate();
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
