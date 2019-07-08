import { EnvLinks } from './envLinks.model';

export class Settings {
  constructor() {
    this.envLinks = new EnvLinks();
  }
  envLinks: EnvLinks;
}
